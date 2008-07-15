package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MoreSwCaseLabel extends GenASTNode {
  public MoreSwCaseLabel(SwCaseLabel swCaseLabel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SwCaseLabel>("swCaseLabel", swCaseLabel)
    }, firstToken, lastToken);
  }
  public MoreSwCaseLabel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MoreSwCaseLabel(cloneProperties(),firstToken,lastToken);
  }
  public SwCaseLabel getSwCaseLabel() {
    return ((PropertyOne<SwCaseLabel>)getProperty("swCaseLabel")).getValue();
  }
}
