import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.drawablemap.present
import com.example.thindie.presentation.util.TimeParser
import com.example.thindie.presentation.weatherpresenter.screen.WeatherSymbol

@Composable
fun SelectedWeatherLocationsScreen(
    isWideScreen: Boolean,
    onSelectedDestination: (String) -> Unit,
    weatherList: List<Weather>,
    onChangePinnedStatus: (String) -> Unit
) {
    if (isWideScreen) LocationsWideScreen(
        weatherList,
        onSelectedDestination,
        onChangePinnedStatus
    ) else LocationsPortraitScreen(
        weatherList,
        onSelectedDestination,
        onChangePinnedStatus
    )
}

@Composable
fun LocationsWideScreen(
    weatherList: List<Weather>,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        LazyColumn() {
            items(weatherList) { weatherPlace ->

                Column(modifier = modifier.fillMaxWidth(0.3f)) {
                    WeatherInformationSection(
                        temperature = weatherPlace.temperature,
                        windspeed = weatherPlace.windspeed,
                        sunrise = weatherPlace.sunrise,
                        sunset = weatherPlace.sunset,
                        place = weatherPlace.place,
                        plus = weatherPlace.isPlus,
                        onSelectedDestination = onChangePinnedStatus,
                    ) { place ->
                        onSelectedDestination(place)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                    WeatherContentRow(
                        weatherCodesHourly = weatherPlace.weatherCodesHourly,
                        timesHourly = weatherPlace.timesHourly,
                        windHourly = weatherPlace.windgusts10mMaxHourly,
                        temperature = weatherPlace.temperatureMaxHourly,
                        temperatureMin = weatherPlace.temperatureMinHourly
                    )
                }
            }
        }

    }
}

@Suppress("LongParameterList")
@Composable
fun WeatherInformationSection(
    temperature: Double,
    windspeed: Double,
    sunrise: List<String>,
    sunset: List<String>,
    place: String,
    plus: Boolean,
    modifier: Modifier = Modifier,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,
) {
    Card(modifier.fillMaxWidth()) {
        Row {
            stringResource(id = R.string.temperature_2m, temperature.toString())
                .HeadLineText(
                    color = if (plus) Color.Red else Color.Blue
                )
            stringResource(id = R.string.place, place).HeadLineText()
            stringResource(id = R.string.wind_speed, windspeed.toString()).BodyLargeText()
            stringResource(id = R.string.sunrise, TimeParser(sunrise.first()).time)
            stringResource(id = R.string.sunset, TimeParser(sunset.first()).time)
        }
    }
}

@Suppress("LongParameterList")
@Composable
fun WeatherContentRow(
    weatherCodesHourly: List<Int>,
    timesHourly: List<String>,
    windHourly: List<Double>,
    temperature: List<Double>,
    temperatureMin: List<Double>,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier = modifier) {
        items(weatherCodesHourly.size) {
            Card {
                WeatherSymbol(
                    weatherRes = weatherCodesHourly[it]
                        .present()
                        .weatherDrawable,
                    modifier = Modifier.size(72.dp)
                )
                Row(){
                    temperature[it].toString().BodyText()
                    temperatureMin[it].toString().BodyText()
                }
                windHourly[it].toString().BodyText()
                TimeParser(timesHourly[it]).time.BodyText()
            }
        }
    }
}

@Composable
fun LocationsPortraitScreen(
    weatherList: List<Weather>,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TODO("Not yet implemented")
}
