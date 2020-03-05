package com.boost.reddit.model

import java.io.Serializable

class TopicModel(): Serializable {

    var id: Int = 0

    var topicText: String = ""

    var upVote: Int = 0

    var downVote: Int = 0

    constructor(id: Int, topic: String, upVote: Int, downVote: Int) : this() {
        this.id = id
        this.topicText = topic
        this.upVote = upVote
        this.downVote = downVote
    }
}