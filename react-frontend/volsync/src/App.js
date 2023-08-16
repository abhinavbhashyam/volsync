import './App.css';
import { Navbar, Nav, Container } from 'react-bootstrap'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import VolunteerRegistration from './components/VolunteerRegistration';
import OrganizationRegistration from './components/OrganizationRegistration';
import Login from './components/Login';
import VolunteerDashboard from './components/VolunteerDashboard';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <>
          <Navbar bg="dark" data-bs-theme="dark">
            <Container>
              <Navbar.Brand href="#home">Volsync</Navbar.Brand>
              <Nav className="me-auto">
                <Nav.Link as={Link} to="/">Login</Nav.Link>
                <Nav.Link as={Link} to="/volunteer_registration">Volunteer Registration</Nav.Link>
                <Nav.Link as={Link} to="/organization_registration">Organization Registration</Nav.Link>
              </Nav>
            </Container>
          </Navbar>
        </>

        <div>
          <Routes>
            <Route path="/volunteer_registration" element={<VolunteerRegistration />}></Route>
            <Route path="/organization_registration" element={<OrganizationRegistration />}></Route>
            <Route path="/" element={<Login />}></Route>
            <Route path="/volunteer_dashboard/:username" element={<VolunteerDashboard />}></Route>
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;
