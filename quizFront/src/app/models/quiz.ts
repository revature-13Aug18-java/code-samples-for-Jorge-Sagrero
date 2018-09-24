import { CreateQuestion } from './create-question';
import { Account } from './account';

export class Quiz {

    quizId: Number;
    quizName: String;
    lockStatus = 0;
    questions: String;
    creatror: Account;

    addQuestion(questionId: number) {
        if (this.questions.length) {
            this.questions += ',' + questionId;
        } else{
            
        }
    }

}
