package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class atom8 extends atom {
  public atom8(StringNode stringNode, ArrayList<StringNode> stringNode1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<StringNode>("stringNode", stringNode),
      new PropertyZeroOrMore<StringNode>("stringNode1", stringNode1)
    }, firstToken, lastToken);
  }
  public atom8(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new atom8(cloneProperties(),firstToken,lastToken);
  }
  public StringNode getStringNode() {
    return ((PropertyOne<StringNode>)getProperty("stringNode")).getValue();
  }
  public ArrayList<StringNode> getStringNode1() {
    return ((PropertyZeroOrMore<StringNode>)getProperty("stringNode1")).getValue();
  }
}
