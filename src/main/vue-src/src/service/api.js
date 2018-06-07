import axios from 'axios'

const baseUrl = process.env.NODE_ENV !== 'production' ? "http://localhost/api" : "/api";
console.log(baseUrl);

const instance = axios.create({
  baseURL: baseUrl,
  timeout: 1000,
  headers: {
    'Access-Control-Allow-Origi': '*',
    'Content-Type': 'application/json'
  },
  mode: 'no-cors',
  withCredentials: true,
  credentials: 'same-origin'
});

export default instance
