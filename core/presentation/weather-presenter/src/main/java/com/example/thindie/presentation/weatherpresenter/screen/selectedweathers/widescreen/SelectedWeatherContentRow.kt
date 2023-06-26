import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.drawablemap.toWeatherPresenter
import com.example.thindie.presentation.util.TimeParser
import com.example.thindie.presentation.weatherpresenter.screen.concreteweather.weathercomposablesections.WeatherSymbol
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.displayText
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.temperatureColor
import com.example.thindie.presentation.weatherpresenter.screen.selectedweathers.windColor


@Suppress("LongParameterList")
@Composable
fun WeatherContentRow(
    weatherCodesHourly: List<Int>,
    timesHourly: List<String>,
    windHourly: List<Double>,
    temperature: List<Double>,
    temperatureMin: List<Double>,
    modifier: Modifier = Modifier,
    timeNow: String,
) {
    val indexCountFrom = timesHourly.indexOfFirst { timesInList -> timeNow == timesInList }
    val indicesList = mutableListOf<Int>().apply {
        for (index in indexCountFrom..25 + indexCountFrom) {
            add(index)
        }
    }
    Card(
        modifier = modifier.padding(top = 1.dp, bottom = 1.dp), shape = ShapeDefaults.ExtraLarge
    ) {
        LazyRow(
            modifier = modifier.padding(
                start = 25.dp, end = 22.dp, top = 4.dp, bottom = 4.dp
            )
        ) {
            items(indicesList) { atIndex ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .padding(all = 2.dp)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    TimeParser(timesHourly[atIndex]).time.BodyText()
                    WeatherSymbol(
                        weatherRes = weatherCodesHourly[atIndex].toWeatherPresenter().weatherDrawable,
                        modifier = Modifier.size(72.dp)
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        temperature.displayText(
                            index = atIndex,
                            color = temperatureColor(temperature[atIndex]),
                            metric = "c"
                        )
                        windHourly.displayText(
                            index = atIndex, color = windColor(windHourly[atIndex]), metric = "m/s"
                        )

                    }
                }

            }
        }
    }
}






