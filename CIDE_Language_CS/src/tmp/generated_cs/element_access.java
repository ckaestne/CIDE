package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class element_access extends GenASTNode {
  public element_access(argument_list argument_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<argument_list>("argument_list", argument_list)
    }, firstToken, lastToken);
  }
  public element_access(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new element_access(cloneProperties(),firstToken,lastToken);
  }
  public argument_list getArgument_list() {
    return ((PropertyOne<argument_list>)getProperty("argument_list")).getValue();
  }
}
