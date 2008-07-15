package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class StateSet extends GenASTNode {
  public StateSet(StatesSpecifier statesSpecifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StatesSpecifier>("statesSpecifier", statesSpecifier)
    }, firstToken, lastToken);
  }
  public StateSet(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new StateSet(cloneProperties(),firstToken,lastToken);
  }
  public StatesSpecifier getStatesSpecifier() {
    return ((PropertyOne<StatesSpecifier>)getProperty("statesSpecifier")).getValue();
  }
}
