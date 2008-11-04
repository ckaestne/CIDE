package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class paramtype extends GenASTNode {
  public paramtype(ArrayList<type> type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<type>("type", type)
    }, firstToken, lastToken);
  }
  public paramtype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new paramtype(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<type> getType() {
    return ((PropertyOneOrMore<type>)getProperty("type")).getValue();
  }
}
