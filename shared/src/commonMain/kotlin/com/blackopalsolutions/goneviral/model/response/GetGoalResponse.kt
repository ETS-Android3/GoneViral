package com.blackopalsolutions.goneviral.model.response

import com.blackopalsolutions.goneviral.model.domain.Goal

class GetGoalResponse : Response {
    val goal: Goal?

    constructor(goal: Goal) : super(true) {
        this.goal = goal
    }

    constructor(message: String) : super(message) {
        goal = null
    }
}