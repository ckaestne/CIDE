package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class and_test extends GenASTNode {
  public and_test(not_test not_test, ArrayList<not_test> not_test1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<not_test>("not_test", not_test),
      new PropertyZeroOrMore<not_test>("not_test1", not_test1)
    }, firstToken, lastToken);
  }
  public and_test(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new and_test(cloneProperties(),firstToken,lastToken);
  }
  public not_test getNot_test() {
    return ((PropertyOne<not_test>)getProperty("not_test")).getValue();
  }
  public ArrayList<not_test> getNot_test1() {
    return ((PropertyZeroOrMore<not_test>)getProperty("not_test1")).getValue();
  }
}
