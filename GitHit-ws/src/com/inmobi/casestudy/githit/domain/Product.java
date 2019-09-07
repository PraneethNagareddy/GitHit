package com.inmobi.casestudy.githit.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"name",
"description",
"itemsRemaining"
})
public class Product {

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("description")
private String description;
@JsonProperty("itemsRemaining")
private Integer itemsRemaining;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public Product(Integer id, String name, String description, Integer itemsRemaining) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.itemsRemaining = itemsRemaining;
}

public Product() {
}


public Product(String name, String description) {
	super();
	this.name = name;
	this.description = description;
}



@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("itemsRemaining")
public Integer getItemsRemaining() {
return itemsRemaining;
}

@JsonProperty("itemsRemaining")
public void setItemsRemaining(Integer itemsRemaining) {
this.itemsRemaining = itemsRemaining;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}