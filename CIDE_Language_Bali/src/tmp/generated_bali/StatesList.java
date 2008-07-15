package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StatesList extends GenASTNode {
  public StatesList(ArrayList<StateName> stateName, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<StateName>("stateName", stateName)
    }, firstToken, lastToken);
  }
  public StatesList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StatesList(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<StateName> getStateName() {
    return ((PropertyList<StateName>)getProperty("stateName")).getValue();
  }
}
