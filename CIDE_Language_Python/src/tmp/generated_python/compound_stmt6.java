package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class compound_stmt6 extends compound_stmt {
  public compound_stmt6(classdef classdef, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<classdef>("classdef", classdef)
    }, firstToken, lastToken);
  }
  public compound_stmt6(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new compound_stmt6(cloneProperties(),firstToken,lastToken);
  }
  public classdef getClassdef() {
    return ((PropertyOne<classdef>)getProperty("classdef")).getValue();
  }
}
