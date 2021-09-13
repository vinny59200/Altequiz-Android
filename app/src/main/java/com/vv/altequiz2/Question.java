package com.vv.altequiz2;

import java.util.Objects;

public class Question {

        private int id;
        private String question;
        private String choices_content;
        private String answer;
        private int karma;
        private int choices_count;

        public Question(int id,String question,int choices_count,String choices_content,String answer,int karma){
            this.id=id;
            this.question=question;
            this.choices_count=choices_count;
            this.choices_content=choices_content;
            this.answer=answer;
            this.karma=karma;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoices_content() {
            return choices_content;
        }

        public void setChoices_content(String choices_content) {
            this.choices_content = choices_content;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getKarma() {
            return karma;
        }

        public void setKarma(int karma) {
            this.karma = karma;
        }

        public int getChoices_count() {
            return choices_count;
        }

        public void setChoices_count(int choices_count) {
            this.choices_count = choices_count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Question question1 = (Question) o;
            return id == question1.id &&
                    choices_count == question1.choices_count &&
                    question.equals(question1.question) &&
                    choices_content.equals(question1.choices_content) &&
                    answer.equals(question1.answer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, question, choices_content, answer, choices_count);
        }

        @Override
        public String toString() {
            return "Question{" +
                    "id=" + id +
                    ", question='" + question + '\'' +
                    ", choices_content='" + choices_content + '\'' +
                    ", answer='" + answer + '\'' +
                    ", karma=" + karma +
                    ", choices_count=" + choices_count +
                    '}';
        }
}
