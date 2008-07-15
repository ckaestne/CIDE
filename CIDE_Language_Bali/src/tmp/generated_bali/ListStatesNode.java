package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ListStatesNode extends StatesSpecifier {
  public ListStatesNode(StatesList statesList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StatesList>("statesList", statesList)
    }, firstToken, lastToken);
  }
  public ListStatesNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ListStatesNode(cloneProperties(),firstToken,lastToken);
  }
  public StatesList getStatesList() {
    return ((PropertyOne<StatesList>)getProperty("statesList")).getValue();
  }
}
