package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constructor_initializer extends GenASTNode {
  public constructor_initializer(constructor_initializerInt constructor_initializerInt, argument_list argument_list, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<constructor_initializerInt>("constructor_initializerInt", constructor_initializerInt),
      new PropertyZeroOrOne<argument_list>("argument_list", argument_list)
    }, firstToken, lastToken);
  }
  public constructor_initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new constructor_initializer(cloneProperties(),firstToken,lastToken);
  }
  public constructor_initializerInt getConstructor_initializerInt() {
    return ((PropertyOne<constructor_initializerInt>)getProperty("constructor_initializerInt")).getValue();
  }
  public argument_list getArgument_list() {
    return ((PropertyZeroOrOne<argument_list>)getProperty("argument_list")).getValue();
  }
}
