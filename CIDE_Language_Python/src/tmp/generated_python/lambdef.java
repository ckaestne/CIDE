package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class lambdef extends GenASTNode {
  public lambdef(ASTStringNode lambda, varargslist varargslist, test test, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("lambda", lambda),
      new PropertyZeroOrOne<varargslist>("varargslist", varargslist),
      new PropertyOne<test>("test", test)
    }, firstToken, lastToken);
  }
  public lambdef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new lambdef(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getLambda() {
    return ((PropertyOne<ASTStringNode>)getProperty("lambda")).getValue();
  }
  public varargslist getVarargslist() {
    return ((PropertyZeroOrOne<varargslist>)getProperty("varargslist")).getValue();
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
}
