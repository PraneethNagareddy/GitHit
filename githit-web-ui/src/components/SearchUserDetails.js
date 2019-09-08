import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';
import * as Constants from '../constants/CommonConstants.js';

class SearchUserDetails extends Component{
   constructor(props) {
	    super(props);

	    this.state = {
	    	  handle: "",
	    	  userDetails : {}
	    };
	}
   
   searchDetails = event => {
	    event.preventDefault();
	    var apiURL = Constants.GITHIT_APPLICATION_BASE_URL + Constants.GET_USER_DETAILS_API_URL;
	    var self = this;
	    axios
	    		.get(apiURL+self.state.handle+'?session_id='+localStorage.getItem('session_id'))
	    		.then(function (response) {
	    			console.log(response);
	    			if(response.data.status
	    					&& response.data.status != 200){
	    				
	    				alert("Unable to fetch details");
	    				console.log("Unable to fetch details");
	    			}
	    			else if(response.status == 200){
	    				console.log("fetch successful");
	    				self.setState({ userDetails: response.data });
	    				self.props.onSearch();
	    			}else{
	    				alert("Unable to fetch details");
	    				console.log("Unable to fetch details");
	    			}
	    		}).catch(function (error) {
	    			console.log(error);
	    		});
	 }
   
   	handleChange = event => {
   		this.setState({
   	      [event.target.name]: event.target.value
   	    });
   	}
  
	render(){
		return( <div style={{border:"1px solid black"}}>
					<h3>
						Search
					</h3>
					Enter Git handle to search for details:<input type="text" name="handle" onChange={this.handleChange}/><br/>
					<Button type="button" onClick={this.searchDetails}>
        					search
        				</Button><br/><br/>
        				<h4>	
        					Details:
        				</h4>
        				<p>{JSON.stringify(this.state.userDetails, null, '\t')}</p>
				</div>
				);
	}
}

export default SearchUserDetails;