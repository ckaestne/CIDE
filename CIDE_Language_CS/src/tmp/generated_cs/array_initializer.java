package tmp.generated_cs;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class array_initializer extends GenASTNode {
  public array_initializer(rest_of_array_initializer rest_of_array_initializer, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<rest_of_array_initializer>("rest_of_array_initializer", rest_of_array_initializer)
    }, firstToken, lastToken);
  }
  public array_initializer(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new array_initializer(cloneProperties(),firstToken,lastToken);
  }
  public rest_of_array_initializer getRest_of_array_initializer() {
    return ((PropertyOne<rest_of_array_initializer>)getProperty("rest_of_array_initializer")).getValue();
  }
}
