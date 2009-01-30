package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class dictmaker extends GenASTNode {
  public dictmaker(test test, test test1, ArrayList<dictInt> dictInt, ASTTextNode text590, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<test>("test1", test1),
      new PropertyZeroOrMore<dictInt>("dictInt", dictInt),
      new PropertyZeroOrOne<ASTTextNode>("text590", text590)
    }, firstToken, lastToken);
  }
  public dictmaker(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new dictmaker(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public test getTest1() {
    return ((PropertyOne<test>)getProperty("test1")).getValue();
  }
  public ArrayList<dictInt> getDictInt() {
    return ((PropertyZeroOrMore<dictInt>)getProperty("dictInt")).getValue();
  }
  public ASTTextNode getText590() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text590")).getValue();
  }
}
