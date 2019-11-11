package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.NotBlank

data class Person(@JsonProperty("id") val id: UUID? = null,
                  @JsonProperty("name") @NotBlank val Name: String) {

}