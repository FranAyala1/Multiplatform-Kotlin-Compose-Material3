package org.crow.model.Game

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PackageGroupDto(
    @SerialName("id")
    private var id: Long? = null,
    @SerialName("name")
    private var name: String? = null,
    @SerialName("title")
    private var title: String? = null,
    @SerialName("description")
    private var description: String? = null,
    @SerialName("selection_text")
    private var selectionText: String? = null,
    @SerialName("save_text")
    private var saveText: String? = null,
    @SerialName("display_type")
    private var displayType: Int? = null,
    @SerialName("is_recurring_subscription")
    private var isRecurringSubscription: String? = null,
    @SerialName("subs")
    private var subs: Set<SubDto>? = null
) {
}