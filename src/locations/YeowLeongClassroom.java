package locations;

import cards.Card;

public class YeowLeongClassroom extends Location {
    public YeowLeongClassroom() {
        super.setName("Yeow Leong's Classroom");
        super.setDescription("All cards placed here have their values set to 1.");
    }

    @Override
    public void placeCard(Card cardToBePlaced, boolean p1) {
        cardToBePlaced.setPower(1);
        super.placeCard(cardToBePlaced, p1);
    }
}
