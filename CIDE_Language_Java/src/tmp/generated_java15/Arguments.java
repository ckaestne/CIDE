package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Arguments extends GenASTNode {
  public Arguments(ArgumentList argumentList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ArgumentList>("argumentList", argumentList)
    }, firstToken, lastToken);
  }
  public Arguments(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Arguments(cloneProperties(),firstToken,lastToken);
  }
  public ArgumentList getArgumentList() {
    return ((PropertyZeroOrOne<ArgumentList>)getProperty("argumentList")).getValue();
  }
}
