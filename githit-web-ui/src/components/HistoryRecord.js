import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';
import * as Constants from '../constants/CommonConstants.js';

class HistoryRecord extends Component{
	constructor(props) {
	    super(props);

	    this.state = {
	      showComponent: true
	    };
	  }
	
	handleDelete = event => {
	    event.preventDefault();
	    var apiURL = Constants.GITHIT_APPLICATION_BASE_URL + Constants.DELETE_HISTORY_RECORD_API_URL;// "http://localhost:8080/githit/deleteHistoryRecord/";
	    var self = this;
	    axios
	    		.delete(apiURL+self.props.id+'?session_id='+localStorage.getItem('session_id'))
	    		.then(function (response) {
	    			console.log(response);
	    			if(response.data.status == 200 ){
	    				console.log("delete successfull");
	    				self.setState({ showComponent: false });
	    			}
	    			else {
	    				alert("Unable to delete");
	    				console.log("Unable to Login");
	    			}
	    		}).catch(function (error) {
	    			console.log(error);
	    		});
	 }
	
	render(){
		if(this.state.showComponent){
			return( <div style={{border:"0.5px dotted black"}}>
						<b>{this.props.handle}</b>
						<Button type="button" onClick={this.handleDelete}>
		            			delete
		            		</Button>
		            	</div>
				);
		}else{
			return(<span></span>);
		}
	}
}

export default HistoryRecord;