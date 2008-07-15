package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class invocation_expression extends GenASTNode {
  public invocation_expression(argument_list argument_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<argument_list>("argument_list", argument_list)
    }, firstToken, lastToken);
  }
  public invocation_expression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new invocation_expression(cloneProperties(),firstToken,lastToken);
  }
  public argument_list getArgument_list() {
    return ((PropertyZeroOrOne<argument_list>)getProperty("argument_list")).getValue();
  }
}
