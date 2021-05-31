import React, {Component} from "react";
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from "react-router-dom";

export default class NavigationBar extends Component{
    render() {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                    <img src="https://www.freeiconspng.com/uploads/sport-car-icon-0.png" width="25" height="25" alt="brand"/>Ripcar Service
                </Link>
                <Nav className="mr-auto">
                    <Link to={"add"} className="nav-link">Reservation</Link>
                    <Link to={"list"} className="nav-link">Reservation List</Link>
                </Nav>
            </Navbar>
        );
    }
}
