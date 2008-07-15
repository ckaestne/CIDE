package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fixed_parameter extends GenASTNode {
  public fixed_parameter(parameter_modifier parameter_modifier, type type, identifier identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<parameter_modifier>("parameter_modifier", parameter_modifier),
      new PropertyOne<type>("type", type),
      new PropertyOne<identifier>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public fixed_parameter(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fixed_parameter(cloneProperties(),firstToken,lastToken);
  }
  public parameter_modifier getParameter_modifier() {
    return ((PropertyZeroOrOne<parameter_modifier>)getProperty("parameter_modifier")).getValue();
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public identifier getIdentifier() {
    return ((PropertyOne<identifier>)getProperty("identifier")).getValue();
  }
}
