import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';
import HistoryRecord from './HistoryRecord';
import * as Constants from '../constants/CommonConstants.js';

class History extends Component{
	constructor(props) {
	    super(props);
	    this.state = {
	      records: []
	    };
	    this.getHistory = this.getHistory.bind(this);
	  }
	
	getHistory(){
		var apiURL = Constants.GITHIT_APPLICATION_BASE_URL + Constants.GET_HISTORY_API_URL;
	    var self = this;
	    axios
	    	.get(apiURL+'?session_id='+localStorage.getItem('session_id'))
	    	.then(function (response) {
	    		console.log(response);
	    		if(response.data.status == 200 ){
	    			console.log("retrieved history successfull");
	    			self.setState({ records: response.data.data });
	    		}
	    		else {
	    			alert("Unable to retrieve history");
	    			self.setState({ records: [] });
	    		}
	    	}).catch(function (error) {
	    		console.log(error);
	    	});
	}
	
	componentWillMount(){
		console.log('Component WILL MOUNT!');
		this.getHistory();
	}
	
	
	
	render(){
		return( <div style={{border:"1px solid black", width:500}}>
					<h3>
						History 
						<Button type="button" onClick={this.getHistory}>
            					Refresh
            				</Button>
					</h3>
					{this.state.records.map(function(record, i){
						return <HistoryRecord handle={record.git_handle} id={record.id}/>
					})}
				</div>
				);
	}
}

export default History;