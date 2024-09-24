import { createBrowserRouter } from "react-router-dom";
import LandingPage from "./views/LandingPage";
import Login from "./views/Login";
import Register from "./views/Register";
import Employee from "./views/Employee";
import EmployeeId from "./views/EmployeeId";


export const router = createBrowserRouter([
    {
      path: "/",
      element: <LandingPage/>,
    },
    {
      path: "/login",
      element: <Login/>,
    },
    {
      path: "/register",
      element: <Register/>,
    },
    {
      path: "/employee",
      element: <Employee/>
    },
    {
      path: "/employee/:id",
      element: <EmployeeId/>
    }
  ]);