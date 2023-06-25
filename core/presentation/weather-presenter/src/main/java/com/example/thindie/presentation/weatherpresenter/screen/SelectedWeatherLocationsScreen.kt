import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.R
import com.example.thindie.presentation.designsystem.textutil.BodyLargeBoldText
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.drawablemap.toWeatherPresenter
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
        weatherList, onSelectedDestination, onChangePinnedStatus
    ) else LocationsPortraitScreen(
        weatherList, onSelectedDestination, onChangePinnedStatus
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

                Column(modifier = modifier.fillMaxWidth()) {
                    WeatherInformationSection(
                        temperature = weatherPlace.temperature,
                        windspeed = weatherPlace.windspeed,
                        sunrise = weatherPlace.sunrise,
                        sunset = weatherPlace.sunset,
                        place = weatherPlace.place,
                        plus = weatherPlace.isPlus,
                        precipitation = weatherPlace.precipitationSum,
                        wind = weatherPlace.windgusts10mMaxHourly,
                        onSelectedDestination = onChangePinnedStatus,
                    ) { place ->
                        onSelectedDestination(place)
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    WeatherContentRow(
                        weatherCodesHourly = weatherPlace.weatherCodesHourly,
                        timesHourly = weatherPlace.timesHourly,
                        windHourly = weatherPlace.windgusts10mMaxHourly,
                        temperature = weatherPlace.temperatureMaxHourly,
                        temperatureMin = weatherPlace.temperatureMinHourly,
                        timeNow = weatherPlace.time
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
    precipitation: List<Double>,
    wind: List<Double>,
    onSelectedDestination: (String) -> Unit,
    onChangePinnedStatus: (String) -> Unit,

    ) {
    Card(modifier.fillMaxWidth(), shape = ShapeDefaults.ExtraLarge) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(80.dp)
                .padding(
                    start = 25.dp, end = 22.dp, top = 4.dp, bottom = 4.dp
                )
        ) {
            stringResource(id = R.string.place_short, place).HeadLineText()
            stringResource(id = R.string.temperature_short, temperature.toString())
                .HeadLineText(
                    color = if (plus) Color.Red else Color.Blue
                )
            stringResource(id = R.string.precipit_short, checkPerticipation(precipitation))
                .BodyLargeText()
            stringResource(id = R.string.wind_short, checkWind(wind)).BodyLargeText()
            stringResource(id = R.string.sunrise).BodyLargeText()
            TimeParser(sunrise.first()).time.BodyLargeText()
            stringResource(id = R.string.sunset).BodyLargeText()
            TimeParser(sunset.first()).time.BodyLargeText()
            IconButton(onClick = { onChangePinnedStatus(place) }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "")
            }
            IconButton(onClick = { onSelectedDestination(place) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        }
    }
}

private fun checkPerticipation(precipitation: List<Double>): String {
    return if (precipitation.any { it > 0.0 }) "ожидаются"
    else "не ожидаются"
}

private fun checkWind(wind: List<Double>): String {
    return wind.max().toString()
}

@Suppress("LongParameterList")
@Composable
fun WeatherContentRow(
    weatherCodesHourly: List<Int>,
    timesHourly: List<String>,
    windHourly: List<Double>,
    temperature: List<Double>,
    temperatureMin: List<Double>,
    modifier: Modifier = Modifier,
    timeNow: String
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
                    modifier = modifier.padding(all = 2.dp)
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

@Composable
private fun windColor(windPower: Double): Color {
    return when (windPower) {
        in 0.0..8.0 -> MaterialTheme.colorScheme.onSurface
        in 8.1..15.1 -> MaterialTheme.colorScheme.onTertiaryContainer
        in 15.1..20.1 -> Color.Magenta
        in 20.1..Double.MAX_VALUE -> Color.Red
        else -> Color.Yellow
    }
}


@Composable
private fun temperatureColor(temperature: Double): Color {
    return when (temperature) {
        in 0.1..3.0 -> MaterialTheme.colorScheme.error.copy(0.8f)
        in 3.1..9.0 -> MaterialTheme.colorScheme.error
        in 9.1..19.0 -> Color.Red.copy(0.8f)
        in 19.1..Double.MAX_VALUE -> Color.Red
        in -0.1..-3.0 -> MaterialTheme.colorScheme.primary.copy(0.3f)
        in -3.1..-9.0 -> MaterialTheme.colorScheme.primary.copy(0.6f)
        in -9.1..-19.0 -> Color.Blue
        in -19.1..Double.MIN_VALUE -> Color.Blue
        else -> MaterialTheme.colorScheme.onSurface
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

@Composable
private fun <E> List<E>.displayText(index: Int, color: Color, metric: String) {
    this[index].toString().plus(" $metric").BodyLargeBoldText(color = color)
}
