package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class classdef extends GenASTNode {
  public classdef(Name name, testlist testlist, suite suite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Name>("name", name),
      new PropertyZeroOrOne<testlist>("testlist", testlist),
      new PropertyOne<suite>("suite", suite)
    }, firstToken, lastToken);
  }
  public classdef(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new classdef(cloneProperties(),firstToken,lastToken);
  }
  public Name getName() {
    return ((PropertyOne<Name>)getProperty("name")).getValue();
  }
  public testlist getTestlist() {
    return ((PropertyZeroOrOne<testlist>)getProperty("testlist")).getValue();
  }
  public suite getSuite() {
    return ((PropertyOne<suite>)getProperty("suite")).getValue();
  }
}
