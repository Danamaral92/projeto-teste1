import axios from "axios";
import Paths from "@/constants/paths.ts";


export const api = axios.create({
    baseURL: "/api",
});