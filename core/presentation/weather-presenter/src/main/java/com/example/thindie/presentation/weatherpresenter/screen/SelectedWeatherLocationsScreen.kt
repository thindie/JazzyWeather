import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.thindie.domain.weatherprovider.entity.Weather

@Composable
fun SelectedWeatherLocationsScreen(
    isWideScreen: Boolean,
    onSelectedDestination: (String) -> Unit,
    weatherList: List<Weather>,
    onChancePinnedStatus: (String) -> Unit
) {
    LazyColumn(){
        items(weatherList){
            Text(text = it.place)
        }
    }

}