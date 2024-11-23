import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopkathon.team2.presentation.model.Route
import com.sopkathon.team2.presentation.ui.home.HomeScreen
import com.sopkathon.team2.presentation.ui.main.MainNavigator

@Composable
fun MainNavHost(
    viewModel: InstagramShareViewModel,
    navigator: MainNavigator, padding: PaddingValues,
    shareClicked: () -> Unit = {}
) {
    NavHost(
        navController = navigator.navController,
        startDestination = Route.Home::class.qualifiedName!!
    ) {
        composable(Route.Home::class.qualifiedName!!) {
            HomeScreen(instaViewModel = viewModel,
                modifier = Modifier.padding(padding),
                onNavigateToProfile = { navigator.navigate(Route.Profile) },
                onNavigateToWrite = { navigator.navigate(Route.Write) },
                shareClicked = { shareClicked() }
            )

        }
        composable(Route.Profile::class.qualifiedName!!) {
//            ProfileScreen(modifier = Modifier.padding(padding),onNavigateToHome = { navigator.navigate(Route.Home) })
        }
        composable(Route.Write::class.qualifiedName!!) {
//            WriteScreen(modifier = Modifier.padding(padding),onNavigateToComplete = { navigator.navigate(Route.Complete) })
        }
        composable(Route.Complete::class.qualifiedName!!) {
//            CompleteScreen(
//                modifier = Modifier.padding(padding),
//                onNavigateToHome = { navigator.navigateAndClearStack(Route.Home) })
        }
    }
}