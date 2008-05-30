package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class klasseTypeVar1 extends klasseTypeVar {
  public klasseTypeVar1(var var, ArrayList<type> type, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<var>("var", var),
      new PropertyZeroOrMore<type>("type", type)
    }, firstToken, lastToken);
  }
  public klasseTypeVar1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new klasseTypeVar1(cloneProperties(),firstToken,lastToken);
  }
  public var getVar() {
    return ((PropertyOne<var>)getProperty("var")).getValue();
  }
  public ArrayList<type> getType() {
    return ((PropertyZeroOrMore<type>)getProperty("type")).getValue();
  }
}
