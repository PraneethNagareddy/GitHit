import React, { Component } from "react";
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import axios from 'axios';
import Header from './Header';
import History from './History';
import SearchUserDetails from './SearchUserDetails'
import Login from './Login'

class Main extends Component{
	constructor(props) {
	    super(props);

	    this.onSearch = this.onSearch.bind(this);
	    this.onLogin = this.onLogin.bind(this);
	}
	render(){
		if(localStorage.getItem('session_id')){
			return( <div>
					<Header/><br/>
					<table>
						<tr>
							<td> <History/> </td>
							<td width={300}> </td>
							<td> <SearchUserDetails onSearch={this.onSearch}/> </td>
						</tr>
					</table>	
				</div>
				);
		}else{
			return (<Login onLogin = {this.onLogin}/>);
		}
	}

	onSearch() {
		
   	}
	
	onLogin(){
		window.location.reload();
	}
	
	componentWillMount(){
		console.log('Component WILL MOUNT!');
	}
}

export default Main;