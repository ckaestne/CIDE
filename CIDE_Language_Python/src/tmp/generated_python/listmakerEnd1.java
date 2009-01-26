package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class listmakerEnd1 extends listmakerEnd {
  public listmakerEnd1(ArrayList<list_for> list_for, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<list_for>("list_for", list_for)
    }, firstToken, lastToken);
  }
  public listmakerEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new listmakerEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<list_for> getList_for() {
    return ((PropertyOneOrMore<list_for>)getProperty("list_for")).getValue();
  }
}
