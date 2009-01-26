package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class except_clause extends GenASTNode {
  public except_clause(testcommatest testcommatest, suite suite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<testcommatest>("testcommatest", testcommatest),
      new PropertyOne<suite>("suite", suite)
    }, firstToken, lastToken);
  }
  public except_clause(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new except_clause(cloneProperties(),firstToken,lastToken);
  }
  public testcommatest getTestcommatest() {
    return ((PropertyZeroOrOne<testcommatest>)getProperty("testcommatest")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
}
