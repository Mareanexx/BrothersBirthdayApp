package ru.mareanexx.brothersbirthdayapp.ui.view.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.mareanexx.brothersbirthdayapp.R
import ru.mareanexx.brothersbirthdayapp.ui.theme.MontserratFamily
import ru.mareanexx.brothersbirthdayapp.ui.theme.Shapes
import ru.mareanexx.brothersbirthdayapp.ui.theme.bottomNav
import ru.mareanexx.brothersbirthdayapp.ui.theme.navBarItems


@Composable
fun BottomNavBar(
    navController: NavController?,
    numberOfSkipped: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .systemBarsPadding()
            .padding(horizontal = 10.dp)
            .background(color = bottomNav, shape = Shapes.medium)
            .fillMaxWidth()
            .height(76.dp)
            .padding(horizontal = 8.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        NavigationItem(
            navController = if (numberOfSkipped == 1) null else navController,
            iconResource = R.drawable.sports_esports,
            captionText = R.string.games,
            navigateTo = "games",
            fraction = 0.3f,
            width = 35.dp
        )
        NavigationItem(
            navController = if (numberOfSkipped == 2) null else navController,
            iconResource = R.drawable.home,
            captionText = R.string.home,
            navigateTo = "home",
            fraction = 0.5f,
            width = 30.dp
        )
        NavigationItem(
            navController = if (numberOfSkipped == 3) null else navController,
            iconResource = R.drawable.featured_seasonal_and_gifts,
            captionText = R.string.my_coins,
            navigateTo = "my_coins",
            fraction = 1f,
            width = 30.dp
        )
    }
}

@Composable
fun NavigationItem(
    navController: NavController?,
    @DrawableRes iconResource: Int,
    @StringRes captionText: Int,
    navigateTo: String,
    fraction: Float,
    width: Dp
) {
    val text = stringResource(captionText)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction)
            .clickable {
                navController?.navigate(route = navigateTo) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    launchSingleTop = true
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(height = 30.dp, width = width),
            painter = painterResource(iconResource),
            contentDescription = text
        )
        Text(
            text = text,
            fontFamily = MontserratFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            letterSpacing = 1.sp,
            color = navBarItems
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewBottomNavBar() {
    BottomNavBar(null, 1)
}
