package com.soundscape.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.soundscape.R

@Composable
fun DetailsBody(
    bottomActionViewModel: BottomActionViewModel,
    modifier: Modifier = Modifier
) {
    val location = bottomActionViewModel.location
    Card() {
        Column {
            Row() {
                AddFavButton({})
                Text(text = location!!.name)
                Text(
                    modifier = modifier.weight(1f),
                    text = location!!.matchScore,
                )
            }
            BarOpeningInfo(location!!)
            Row() {
                Image(
                    painter = painterResource(R.drawable.pub),
                    contentDescription = "${stringResource(id = R.string.location_photo_description)} + ${location.name}"
                )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = location.website)
            }
            HorizontalList(location.genres)
            LocationDescriptionCard(location)
            EventsCard(location)
            Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AddToCrawlButton(location)
                RouteButton(location)
            }
        }
    }
}