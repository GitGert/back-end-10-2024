import logo from './logo.svg';
import './App.css';

function App() {

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then(res => res.json())
      .then()
    // return () => {
    //   second
    // }
  }, [])
  


  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
