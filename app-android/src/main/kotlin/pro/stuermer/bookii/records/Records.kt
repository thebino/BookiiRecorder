package pro.stuermer.bookii.records

import android.media.AudioFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.StopCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.squti.androidwaverecorder.WaveRecorder
import java.io.File
import me.jahnen.libaums.core.UsbMassStorageDevice
import me.jahnen.libaums.core.fs.UsbFileOutputStream
import timber.log.Timber


@Composable
fun Records() {
    val context = LocalContext.current
    val filePath:String = context.filesDir.absolutePath + "/tmpRecord.wav"
    var waveRecorder: WaveRecorder? = null
    LaunchedEffect(key1 = "recored") {

        waveRecorder = WaveRecorder(filePath)
        waveRecorder?.waveConfig?.sampleRate = 44100
        waveRecorder?.waveConfig?.channels = AudioFormat.CHANNEL_IN_MONO
        waveRecorder?.waveConfig?.audioEncoding = AudioFormat.ENCODING_PCM_16BIT
        waveRecorder?.onAmplitudeListener = { maxAmplitude: Int ->
            Timber.i("Amplitude : $maxAmplitude")
        }
        waveRecorder?.onTimeElapsed = { seconds: Long ->
            Timber.v("Seconds : $seconds")
        }
    }
    Column(
        modifier = Modifier.statusBarsPadding(),

    ) {
        Text(
            text = "Records",
            color = MaterialTheme.colorScheme.primary
        )
        Row {
            IconButton(onClick = {
                // TODO: request audio record permission
                waveRecorder?.startRecording()
            }) {
                Icon(Icons.Default.RadioButtonChecked, contentDescription = null)
            }
            IconButton(onClick = {
                waveRecorder?.pauseRecording()

            }) {
                Icon(Icons.Default.PauseCircle, contentDescription = null)
            }
            IconButton(onClick = {
                waveRecorder?.stopRecording()
            }) {
                Icon(Icons.Default.StopCircle, contentDescription = null)
            }
        }
        Button(onClick = {
            Timber.d("start move...")
            val recordFile = File(filePath)

            if (recordFile.exists() && recordFile.isFile && recordFile.length() > 0) {
                // todo: move
                val devices = UsbMassStorageDevice.getMassStorageDevices(context = context /* Context or Activity */)
                Timber.i("devices: ${devices.size}")

                for (d in devices) {
                    // before interacting with a device you need to call init()!
                    d.init()

                    // Only uses the first partition on the device
                    val currentFs = d.partitions[0].fileSystem

                    val root = currentFs.rootDirectory

                    root.search("TestRec870.wav")?.delete()
                    val file = root.createFile("TestRec870.wav")

                    val os = UsbFileOutputStream(file)

                    os.write(recordFile.readBytes())
                    os.close()
                    Timber.i("move done..")

                    d.close()
                }
            } else {
                // todo: error message
                Timber.e("Failed to move!")
            }
        }) {
            Text(text = "Move to Pen")
        }
    }
}
