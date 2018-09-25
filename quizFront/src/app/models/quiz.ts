import { CreateQuestion } from './create-question';
import { Account } from './account';

export class Quiz {

    quizId: Number;
    quizName: String;
    lockStatus = 0;
    questions: String = ',';
    creator: Account;

    // public addQuestion(questionId: Number) {
    //     console.log("adding question: " +questionId);
    //     if (this.questions.length) {
    //         console.log("before: " + this.questions);
    //         this.questions +="" + questionId + ',';
    //         console.log("after: " + this.questions);

    //     } else{

    //     }
    // }

}
