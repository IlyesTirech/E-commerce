import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Router, Switch, Route, Link } from "react-router-dom";
import { Nav, NavDropdown, Form, FormControl, Button, Navbar, Container } from "react-bootstrap";
import styled from "styled-components";

import GlobalStyle from "./GlobalStyle";

import Footer from "./components/Footer";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import BoardModerator from "./components/BoardModerator";
import BoardAdmin from "./components/BoardAdmin";

import { logout } from "./actions/auth";
import { clearMessage } from "./actions/message";

import { history } from "./helpers/history";

const App = () => {
  const [showModeratorBoard, setShowModeratorBoard] = useState(false);
  const [showAdminBoard, setShowAdminBoard] = useState(false);

  const { user: currentUser } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    history.listen((location) => {
      dispatch(clearMessage()); // clear message when changing location
    });
  }, [dispatch]);

  useEffect(() => {
    if (currentUser) {
      setShowModeratorBoard(currentUser.roles.includes("ROLE_MODERATOR"));
      setShowAdminBoard(currentUser.roles.includes("ROLE_ADMIN"));
    }
  }, [currentUser]);

  const logOut = () => {
    dispatch(logout());
  };

  return (
    <Router history={history}>
      <GlobalStyle/>
      <div>
      <NavigationBar>
            <Navbar bg="light" expand="lg">
              <Container>
                <Navbar.Brand href="#">MOQN</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll" className="bar-style">
                  <Nav className="mr-auto my-2 my-lg-0" style={{ maxHeight: '250px' }} navbarScroll>
                    <Nav.Link href="/home">Home</Nav.Link>
                    <NavDropdown title="Categories" id="navbarScrollingDropdown">
                      <NavDropdown.Item href="#action3">Action</NavDropdown.Item>
                      <NavDropdown.Item href="#action4">Another action</NavDropdown.Item>
                      <NavDropdown.Divider />
                      <NavDropdown.Item href="#action5">Something else here</NavDropdown.Item>
                    </NavDropdown>
                    {showAdminBoard  && ( <Nav.Link href="/admin">Admin</Nav.Link> )}
                    {currentUser  && ( <Nav.Link href="/profile">Profile</Nav.Link> )}
                    {currentUser  && ( <Nav.Link onClick={logOut} href="/login">Log Out</Nav.Link> )}
                    {!currentUser  && ( <Nav.Link href="/login">Login</Nav.Link> )}
                    {!currentUser  && ( <Nav.Link  href="/register">Sign Up</Nav.Link> )}
                  </Nav>
                  <Form className="d-flex">
                    <FormControl
                      type="search"
                      placeholder="Search"
                      className="mr-2"
                      aria-label="Search"
                      />
                    <Button variant="outline-success">Search</Button>
                  </Form>
                </Navbar.Collapse>
              </Container>
            </Navbar>
      </NavigationBar>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/profile" component={Profile} />
            <Route path="/user" component={BoardUser} />
            <Route path="/mod" component={BoardModerator} />
            <Route path="/admin" component={BoardAdmin} />
          </Switch>
        </div>
      </div>
      <Footer/>
    </Router>
  );
};

const NavigationBar = styled.div`

@media screen and (min-width: 992px) {
  .bar-style{
    display: flex;
    justify-content: space-between;

  }
}



`


export default App;