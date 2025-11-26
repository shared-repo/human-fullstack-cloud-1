export default function Increase(props:{ setCount: React.Dispatch<React.SetStateAction<number>> }) {

  return (
    <>
      <button onClick={ () => {
        props.setCount( (prevCount) => prevCount + 1 )
      }}>증가</button>
      <hr />
    </>    
  )
}