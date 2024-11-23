import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopkathon.team2.data.datasourceimpl.local.ImageLocalDataSourceImpl
import com.sopkathon.team2.presentation.model.Route
import com.sopkathon.team2.presentation.ui.complete.CompleteScreen
import com.sopkathon.team2.presentation.ui.complete.CompleteViewModel
import com.sopkathon.team2.presentation.ui.home.HomeScreen
import com.sopkathon.team2.presentation.ui.main.MainNavigator
import com.sopkathon.team2.presentation.ui.profile.ProfileScreen
import com.sopkathon.team2.presentation.ui.write.WriteScreen
import com.sopkathon.team2.presentation.ui.write.WriteViewModel

@Composable
fun MainNavHost(
    navigator: MainNavigator, padding: PaddingValues
) {
    val context = LocalContext.current
    val dataSource = ImageLocalDataSourceImpl(context=context)
    NavHost(
        navController = navigator.navController,
        startDestination = Route.Home::class.qualifiedName!!
    ) {
        composable(Route.Home::class.qualifiedName!!) {
            HomeScreen(modifier = Modifier.padding(padding),
                onNavigateToProfile = { navigator.navigate(Route.Profile(1)) },
                onNavigateToWrite = { navigator.navigate(Route.Write) },
                onUserClick = {userId ->  navigator.navigate(Route.Profile(userId = userId)) }
            )
        }
        composable(Route.Profile::class.qualifiedName!!) {
            ProfileScreen(
                modifier = Modifier.padding(padding),
                onNavigateToHome = { navigator.navigate(Route.Home) },
                userId = 1
            )
        }
        composable(Route.Write::class.qualifiedName!!) {
            WriteScreen(viewModel = WriteViewModel(dataSource),modifier = Modifier.padding(padding),onNavigateToComplete = { navigator.navigate(Route.Complete) })
        }
        composable(Route.Complete::class.qualifiedName!!) {
            CompleteScreen(
                modifier = Modifier.padding(padding),
                viewModel = CompleteViewModel(dataSource),
                onNavigateToHome = { navigator.navigateAndClearStack(Route.Home) })
        }
    }
}
