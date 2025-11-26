import { Component } from "react";

// react에서 만든 Component 클래스를 상속해서 ClassComponent 만들기
class ClassComponent2 extends Component {

    render() {
        return (
            <>
                <h2>클래스 컴포넌트2 입니다.</h2>
                <button onClick={ () => alert('눌러주셔서 감사합니다.') }>눌러주세요</button>
            </>
        )
    }
}

export default ClassComponent2 // ClassComponent2를 외부에서 사용할 수 있도록 개방