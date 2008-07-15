package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class argument_list extends GenASTNode {
  public argument_list(argument argument, ArrayList<argument> argument1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<argument>("argument", argument),
      new PropertyZeroOrMore<argument>("argument1", argument1)
    }, firstToken, lastToken);
  }
  public argument_list(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new argument_list(cloneProperties(),firstToken,lastToken);
  }
  public argument getArgument() {
    return ((PropertyOne<argument>)getProperty("argument")).getValue();
  }
  public ArrayList<argument> getArgument1() {
    return ((PropertyZeroOrMore<argument>)getProperty("argument1")).getValue();
  }
}
