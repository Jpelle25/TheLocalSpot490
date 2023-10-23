import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '27818980ca80b1c3407aef346663c4af8a739d415fc23bff7806b17b7decfb24') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === 'a954598055101d8210cb9a17ed14fa6932b9c2015c949b04fb7854204afe0005') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === 'e2a95467b562e7746577029bf2858b6a1ecf6ae2e626e2c2cfd324967a61062c') {
    pending.push(import('./chunks/chunk-2b916e01a2644eea96c720354cc50c72ec659aed26f4199d9a5e4e73586f2833.js'));
  }
  if (key === 'd958f98c6448b5d9572f801f11d3d621dd7d034b7dd6b370763db04d26c799a8') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '3ba6fef73b1f58afb71e4ae61fb7220e5319f574f275dd9c930a8cf9a111361d') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '3ee2640c794af158f88900463f415beb3202073a3faaa5338b0383e2c420c0ef') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '28238d35164e19fc1995244f72e2ab66a4b8432e9721d059ec52ce8e552fbff7') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === 'b0186e194af78f6a07917e02b04f522b82ca9343998700ec282270b2f14c58e5') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '46a7fc2ef49efc6ce42619340c31fed47039a642c312cf93d6d81b4a73955be8') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '08b5876f9a4d040748e9c72f02226ee117fde97afe180008fc40bc5056e835e1') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === 'ba5b17a21e6c59d0f7b50d19e7675e3dd461f7701bbeb0f108c8868f7d9e89c0') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '9b62193b19dfd65c8f5f803cede57040081853a782d39f193153430836b95d78') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  if (key === '47ce4eab1033058459110c1dac7654bc0c602a79c394a714062688de6f391732') {
    pending.push(import('./chunks/chunk-e666b5e1b4a9638eb6680d7e0ef5a6c5ac3f59445f944d66d189c9953f7f27e0.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;