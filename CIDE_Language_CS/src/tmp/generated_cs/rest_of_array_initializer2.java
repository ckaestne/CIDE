package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class rest_of_array_initializer2 extends rest_of_array_initializer {
  public rest_of_array_initializer2(variable_initializer variable_initializer, rest_of_array_initializerEnd rest_of_array_initializerEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<variable_initializer>("variable_initializer", variable_initializer),
      new PropertyOne<rest_of_array_initializerEnd>("rest_of_array_initializerEnd", rest_of_array_initializerEnd)
    }, firstToken, lastToken);
  }
  public rest_of_array_initializer2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new rest_of_array_initializer2(cloneProperties(),firstToken,lastToken);
  }
  public variable_initializer getVariable_initializer() {
    return ((PropertyOne<variable_initializer>)getProperty("variable_initializer")).getValue();
  }
  public rest_of_array_initializerEnd getRest_of_array_initializerEnd() {
    return ((PropertyOne<rest_of_array_initializerEnd>)getProperty("rest_of_array_initializerEnd")).getValue();
  }
}
