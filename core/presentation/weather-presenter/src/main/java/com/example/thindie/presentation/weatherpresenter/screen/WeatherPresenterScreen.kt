package com.example.thindie.presentation.weatherpresenter.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.domain.weatherprovider.entity.Weather
import com.example.thindie.presentation.R
import com.example.thindie.presentation.ShortWeatherPresenter
import com.example.thindie.presentation.designsystem.textutil.BodyLargeBoldText
import com.example.thindie.presentation.designsystem.textutil.BodyLargeText
import com.example.thindie.presentation.designsystem.textutil.BodyText
import com.example.thindie.presentation.designsystem.textutil.HeadLineText
import com.example.thindie.presentation.designsystem.textutil.MiniText
import com.example.thindie.presentation.drawablemap.toWeatherPresenter
import com.example.thindie.presentation.util.TimeParser
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.BuildColumnGraph
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.precipitation
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.rainSums
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.showersSums
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.snowFall
import com.example.thindie.presentation.weatherpresenter.weathergraphapi.tempApparentMaximum

@Composable
fun WeatherPresenterScreen(isWideScreen: Boolean, weather: Weather, onClick: (String) -> Unit) {
    if (isWideScreen)
        WeatherPresenterWideScreen(weather = weather) { pinStatus ->
            onClick(pinStatus)
        }
    else WeatherPresenterPortraitScreen(weather = weather) { pinStatus ->
        onClick(pinStatus)
    }
}

@Composable
fun WeatherPresenterPortraitScreen(
    modifier: Modifier = Modifier,
    weather: Weather,
    onClick: (String) -> Unit
) {
    Column(modifier) {
        WeatherPrimarySection(
            place = weather.place,
            weatherCode = weather.weathercode,
            temperature = weather.temperature,
            plus = weather.isPlus,
            sunset = weather.sunset,
            sunrise = weather.sunrise,
            windDirection = weather.winddirection,
            windSpeed = weather.windspeed,
            time = weather.time,
            longitude = weather.longitude,
            latitude = weather.latitude,
        ) { pinStatus ->
            onClick(pinStatus)
        }
        WeatherSlaveSection(
            modifier = modifier,
            longitude = weather.longitude,
            latitude = weather.latitude,
            times = weather.sunrise,
            apparentTemperatureMax = weather.apparentTemperatureMax,
            precipitationSum = weather.precipitationSum,
            rainSum = weather.rainSum,
            showersSum = weather.showersSum,
            precipitationSum1 = weather.precipitationSum,
            snowfallSum = weather.snowfallSum,
            temperatureMax = weather.temperatureMaxHourly,
            temperatureMin = weather.temperatureMinHourly,
            weatherCodes = weather.weatherCodesHourly,
            windspeed10mMax = weather.windspeed10mMaxHourly,
            windgusts10mMax = weather.windgusts10mMaxHourly
        )
    }
}

@Suppress("LongParameterList")
@Composable
fun WeatherSlaveSection(
    modifier: Modifier,
    longitude: Float,
    latitude: Float,
    apparentTemperatureMax: List<Double>,
    precipitationSum: List<Double>,
    rainSum: List<Double>,
    showersSum: List<Double>,
    precipitationSum1: List<Double>,
    snowfallSum: List<Double>,
    temperatureMax: List<Double>,
    temperatureMin: List<Double>,
    weatherCodes: List<Int>,
    windspeed10mMax: List<Double>,
    windgusts10mMax: List<Double>,
    times: List<String>
) {

    CoordinatesSection(longitude = longitude, latitude = latitude)
    times.BuildDaysLine()
    LazyColumn() {
        item {
            graph(apparentTemperatureMax)
            { apparentTemperatureMax.BuildColumnGraph(tempApparentMaximum) }
        }
        item {
            graph(precipitationSum)
            { precipitationSum.BuildColumnGraph(precipitation) }
        }
        item {
            graph(rainSum)
            { rainSum.BuildColumnGraph(rainSums) }

        }
        item {
            graph(showersSum)
            { showersSum.BuildColumnGraph(showersSums) }

        }
        item {
            graph(snowfallSum)
            { snowfallSum.BuildColumnGraph(graphFiller = snowFall) }
        }
    }
}


@Suppress("LongParameterList")
@Composable
fun WeatherPrimarySection(
    place: String,
    weatherCode: Int,
    temperature: Double,
    plus: Boolean,
    sunset: List<String>,
    sunrise: List<String>,
    windDirection: Double,
    windSpeed: Double,
    time: String,
    latitude: Float,
    longitude: Float,
    onClick: (String) -> Unit
) {
    IconButton(
        onClick = { onClick(place) }, modifier = Modifier
            .size(54.dp)
            .padding(start = 8.dp, end = 12.dp, top = 5.dp, bottom = 2.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "",
            Modifier.padding(all = 7.dp)
        )
    }

    TemperatureSection(
        weatherPresenter = weatherCode.toWeatherPresenter(),
        place = place,
        temperature = temperature,
        plus = plus
    )
    AdditionalCurrentWeatherPlaceInfo(
        sunset = sunset.last(),
        sunrise = sunrise.last(),
        windDirection = windDirection,
        windSpeed = windSpeed,
        time = time
    )
}

@Composable
fun AdditionalCurrentWeatherPlaceInfo(
    modifier: Modifier = Modifier,
    sunset: String,
    sunrise: String,
    windDirection: Double,
    windSpeed: Double,
    time: String
) {
    val values = PaddingValues(all = 5.dp)
    Card(
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .padding(values)
            .width(400.dp)
            .height(100.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                stringResource(
                    id = R.string.sunrise, ""
                ).BodyLargeText()
                TimeParser(sunrise).time.BodyLargeBoldText()
                stringResource(
                    id = R.string.sunset, ""
                ).BodyLargeText()
                TimeParser(sunset).time.BodyLargeBoldText()
            }
            Column(modifier = modifier.padding(all = 10.dp)) {
                stringResource(
                    id = R.string.wind_speed,
                    windSpeed.toString()
                ).BodyLargeBoldText()
                stringResource(
                    id = R.string.time_on
                )
                    .BodyLargeText()
                TimeParser(time).time.BodyLargeText()
            }

        }
    }
}


@Composable
fun WeatherPresenterWideScreen(
    modifier: Modifier = Modifier,
    weather: Weather,
    onClick: (String) -> Unit
) {
    Row(modifier.wrapContentHeight()) {
        Column(Modifier.fillMaxWidth(0.5f)) {
            WeatherPrimarySection(
                place = weather.place,
                weatherCode = weather.weathercode,
                temperature = weather.temperature,
                plus = weather.isPlus,
                sunset = weather.sunset,
                sunrise = weather.sunrise,
                windDirection = weather.winddirection,
                windSpeed = weather.windspeed,
                time = weather.time,
                longitude = weather.longitude,
                latitude = weather.latitude,
            ) { pinStatus ->
                onClick(pinStatus)
            }
        }
        Column(Modifier.fillMaxWidth( )) {
            WeatherSlaveSection(
                modifier = modifier,
                longitude = weather.longitude,
                latitude = weather.latitude,
                times = weather.sunrise,
                apparentTemperatureMax = weather.apparentTemperatureMax,
                precipitationSum = weather.precipitationSum,
                rainSum = weather.rainSum,
                showersSum = weather.showersSum,
                precipitationSum1 = weather.precipitationSum,
                snowfallSum = weather.snowfallSum,
                temperatureMax = weather.temperatureMaxHourly,
                temperatureMin = weather.temperatureMinHourly,
                weatherCodes = weather.weatherCodesHourly,
                windspeed10mMax = weather.windspeed10mMaxHourly,
                windgusts10mMax = weather.windgusts10mMaxHourly
            )
        }
    }
}


@Composable
fun WeatherSymbol(modifier: Modifier = Modifier, @DrawableRes weatherRes: Int) {
    Surface(
        modifier = modifier
            .clip(CircleShape)
    ) {
        Icon(
            painter = painterResource(id = weatherRes),
            contentDescription = "",
            modifier = modifier
                .padding(all = 15.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
fun CoordinatesSection(
    modifier: Modifier = Modifier,
    longitude: Float,
    latitude: Float,
) {
    val values = PaddingValues(all = 5.dp)
    Row(modifier = modifier.wrapContentHeight()) {

        Card(
            shape = CircleShape,
            modifier = modifier
                .padding(values)
                .wrapContentSize()
        ) {
            Row(modifier.padding(start = 10.dp, end = 10.dp, top = 4.dp)) {
                stringResource(id = R.string.latitude, latitude)
                    .BodyText(
                        modifier
                            .padding(values)
                    )

            }
            Row(modifier.padding(start = 10.dp, end = 10.dp, bottom = 4.dp)) {
                stringResource(id = R.string.longitude, longitude)
                    .BodyText(
                        modifier
                            .padding(values)
                    )
            }
        }
    }


}

@Composable
fun TemperatureSection(
    modifier: Modifier = Modifier,
    place: String,
    temperature: Double,
    plus: Boolean,
    weatherPresenter: ShortWeatherPresenter
) {
    val values = PaddingValues(all = 5.dp)
    Card(
        shape = ShapeDefaults.ExtraLarge,
        modifier = modifier
            .padding(values)
            .width(400.dp)
            .height(120.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier
                    .fillMaxWidth(0.35f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                WeatherSymbol(
                    weatherRes = weatherPresenter.weatherDrawable,
                    modifier = modifier
                        .size(72.dp),
                )

            }
            Column(modifier = modifier.padding(all = 10.dp)) {
                stringResource(id = R.string.place, "")
                    .MiniText()
                place.HeadLineText()
                weatherPresenter.nameType.BodyText()
                stringResource(
                    id = R.string.temperature_2m,
                    temperature.toString()
                )
                    .HeadLineText(
                        color = if (plus) Color.Red else Color.Blue
                    )
            }

        }
    }
}

@Composable
fun List<String>.BuildDaysLine() {
    val values = PaddingValues(start = 12.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
    OutlinedCard(
        Modifier
            .padding(values)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = ShapeDefaults.ExtraLarge
    ) {
        Row(Modifier.padding(start = 25.dp, end = 8.dp, top = 4.dp)) {

        }
        LazyRow(modifier = Modifier.padding(start = 20.dp, end = 8.dp, bottom = 4.dp)) {
            items(this@BuildDaysLine) {
                TimeParser(it).day.BodyLargeText(
                    modifier = Modifier.padding(start = 11.dp, end = 24.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun previewTemperatureSection() {
    com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme() {
        TemperatureSection(
            place = "place",
            temperature = -5.00,
            plus = false,
            weatherPresenter = 0.toWeatherPresenter()
        )
    }
}

@Preview
@Composable
fun previewCoordinates() {
    com.example.thindie.presentation.designsystem.theme.JazzyWeatherTheme() {
        CoordinatesSection(longitude = 99f, latitude = 55f)
    }
}

@Composable
private fun graph(list: List<Double>, content: @Composable () -> Unit) {
    if (list.any { it != 0.0 }) {
        content()
    }
}